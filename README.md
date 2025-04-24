# 🕸️ CloudNET Web Panel (Legacy)  
![Status](https://img.shields.io/badge/status-archived-lightgrey) ![Version](https://img.shields.io/badge/version-initial-red)

**CloudNET Web Panel** was an early web-based frontend for managing [CloudNET v3](https://cloudnetservice.eu/) networks. This repository is now **archived** and the project has been **discontinued**, having been superseded by [Caesar](https://github.com/JWeinelt/Caesar).

> 🏛️ *“Every experiment leaves its footprints in history.”*

---

## ❓ What was this?

CloudNET Web Panel provided a browser-based UI for:
- Viewing and controlling CloudNET nodes and services  
- Browsing server groups and individual servers  
- Inspecting logs, console output and system metrics  
- Managing basic player permissions  

It leveraged React, Tailwind CSS and CloudNET’s REST API to deliver a lightweight admin console.

---

## 📜 Why archived?

During development we encountered:
- Fragmented API changes in CloudNET v4  
- Performance and security concerns in a public web context  
- Duplication of efforts with a new native client approach  

As a result, this panel was set aside in favor of the standalone **Caesar** client, which offers richer features, tighter security and modular architecture.

---

## 📦 Repository Contents

- **`/src`** – React components & Tailwind setup  
- **`/public`** – Static assets & entry HTML  
- **Early prototypes** of authentication, server-browser and log-viewer  
- API integration stubs for key CloudNET endpoints

---

## ⚠️ Important Notes

- This code **does not work** with the current CloudNET API.  
- **No further updates** or support will be provided here.  
- Pull requests and issues are **disabled** for this archive.

---

## 🤲 Acknowledgments

Thank you to everyone who contributed ideas, bug reports and early feedback.  
Your efforts helped shape the next generation of tools for Minecraft network management.

---

> *“History guides innovation, even when paths diverge.”*

---

## 🔗 See Also

- **Caesar** (the successor, with enhanced features):  
  https://github.com/JWeinelt/Caesar  
- **Caesar Client** (desktop application):  
  https://github.com/JWeinelt/Caesar-Panel  
- **CloudNET v3** official:  
  https://cloudnetservice.eu/
