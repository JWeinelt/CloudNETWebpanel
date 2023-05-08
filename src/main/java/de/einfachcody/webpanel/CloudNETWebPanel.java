package de.einfachcody.webpanel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.module.ModuleLifeCycle;
import de.dytanic.cloudnet.driver.module.ModuleTask;
import de.dytanic.cloudnet.driver.service.*;
import de.dytanic.cloudnet.module.NodeCloudNetModule;

import java.util.stream.Stream;

import static spark.Spark.*;

public class CloudNETWebPanel extends NodeCloudNetModule {
    @ModuleTask(event = ModuleLifeCycle.STARTED)
    public void enableInterface() {
        port(5050);

        get("/allservices", ((request, response) -> {
            return new Gson().toJson(
                    CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices()
                            .stream().flatMap((ser) ->
                                    Stream.of(ser.getName())
                            )
            );
        }));



        get("/service/:id", ((request, response) -> {
            ServiceInfoSnapshot s = CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServiceByName(request.params(":id"));

            response.header("Access-Control-Allow-Origin", "*");
            response.header("Cache-Control", "no-store");
            response.type("application/json");

            JsonObject obj = new JsonObject();

            if(s == null) {
                obj.addProperty("error", "NO_SERVICE");
            } else {
                obj.addProperty("name", s.getName());
                obj.addProperty("serviceId", s.getServiceId().getUniqueId().toString());
                obj.addProperty("node", s.getServiceId().getNodeUniqueId());
                obj.addProperty("taskName", s.getServiceId().getTaskName());
                obj.addProperty("taskId", s.getServiceId().getTaskServiceId());
                obj.addProperty("address", s.getAddress().getHost() + ":" + s.getAddress().getPort());
                obj.addProperty("lifecycle", s.getLifeCycle().toString());
            }

            return obj.toString();
        }));


        get("/task/:id", ((request, response) -> {
            ServiceTask s = CloudNetDriver.getInstance().getServiceTaskProvider().getServiceTask(request.params(":id"));

            response.header("Access-Control-Allow-Origin", "*");
            response.header("Cache-Control", "no-store");
            response.type("application/json");

            JsonObject obj = new JsonObject();

            if(s == null) {
                obj.addProperty("error", "NO_TASK");
            } else {
                obj.addProperty("name", s.getName());
                obj.addProperty("canStartServices", s.canStartServices());
                obj.addProperty("maintenance", s.isMaintenance());
                obj.addProperty("static", s.isStaticServices());
                obj.addProperty("minServiceCount", s.getMinServiceCount());
            }

            return obj.toString();
        }));


        get("/group/:id", (((request, response) -> {
            GroupConfiguration c = CloudNetDriver.getInstance().getGroupConfigurationProvider().getGroupConfiguration(request.params(":id"));

            response.header("Access-Control-Allow-Origin", "*");
            response.header("Cache-Control", "no-store");
            response.type("application/json");

            JsonObject obj = new JsonObject();

            if(c == null) {
                obj.addProperty("error", "NO_GROUP");
            } else {
                obj = JsonParser.parseString(c.getProperties().toJson()).getAsJsonObject();
                c.getProperties().toJson();
            }

            return obj.toString();
        })));

        post("/start/:id", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Cache-Control", "no-store");
            //ServiceInfoSnapshot s = ServiceConfiguration.builder().task(request.params(":id").split("-")[0]).build().createNewService();
            ServiceInfoSnapshot s = ServiceConfiguration.builder().task("Lobby").serviceId(new ServiceId()).build().createNewService();
            s.provider().start();
            return "SUCCESS";
        });

        post("/restart/:id", ((request, response) -> {
            ServiceInfoSnapshot s = CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServiceByName(request.params(":id"));
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Cache-Control", "no-store");
            s.provider().restart();
            return "SUCCESS";
        }));


        getLogger().info("Webinterface has been enabled.");
    }

}