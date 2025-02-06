# 🖼️ Image Editor

## 📝 Overview
This project is an **advanced image editor**, developed for the **Software Engineering 2** course.  
The application follows an **MVC architecture for the frontend** and a **multilayer approach for the backend**, ensuring a clean and scalable software design.

One of the **core strengths** of this editor is its **modern and visually appealing UI**, crafted with **custom CSS styling** to enhance user experience and usability.  
The interface is structured into **four main sections**, providing an intuitive and efficient workflow for image editing.

<p align="center">
  <img src="https://github.com/user-attachments/assets/68315ca1-75e8-405e-bdf8-ae6a80e675f7" height="400px">
</p>

---
## 🎨 Features

### 🛠️ **Image Processing Workflow**
- Users can apply **various transformations** to an image.
- Supported image formats: **PGM, PPM, and PBM**.
- New formats and transformations can be added **easily** by extending the backend (see **Extensibility** below).

### 🖥️ **User Interface Layout**
The UI is divided into four **highly interactive sections**:
1. **Transformation Panel** – Displays all available transformations that can be applied.
2. **Image Display Area** – Shows the image **in real time** with all applied transformations.
3. **Processing Pipeline** – Stores the transformations selected by the user.
    - Clicking the **➕ button** next to a transformation adds it to the pipeline.
    - Pressing the **Execute** button applies the full pipeline of transformations to the image.
    - Pressing the **Clear** button removes all transformations from the pipeline.
4. **Log Console** – Displays a live **history of operations**, logging every action performed.

### 🔄 **Extensibility: Easily Add New Formats & Transformations**
A **key feature** of this software is its **high modularity**:
- **Adding support for new image formats** is as simple as creating a new class inside **`ExtensionsIO`** (Data Access Layer).
- **Adding new transformations** follows the same logic: simply create a new class inside **`transformation`** (Domain Layer).

To ensure compatibility, each new format or transformation **must implement** the following interfaces:
- **`IOInterface`** → Handles **reading and writing** of the image format.
- **`AvTransformationInterface`** → Defines the **transformation algorithm**.

Once implemented, the system **automatically detects** the new additions via **Reflection**, making the extension process seamless.

### 🌍 **Multi-language Support**
- The editor supports both **English and Italian**, allowing users to switch between languages.

### 💾 **Export & Save**
- Users can **export** the modified image after applying transformations.

---
## 🔧 Technologies Used
- **Frontend:** JavaFX (MVC architecture, custom CSS)
- **Backend:** Java (multilayer architecture)
- **Reflection API:** Automatic detection of formats and transformations
- **Persistence:** File-based save system
- **Version Control:** Git, GitHub

---
## 🛠️ How to Run the Project
1. Clone the repository:
```bash
git clone git@github.com:MattiaVerdolin/image-editor.git
```
2. Navigate to the project directory:
```bash
cd image-editor
```
3. Build and run the application using Maven:
```bash
mvn clean install
```

4. Open the application and start to enjoy it!

---
## 👤 Authors
- **Mattia Verdolin** 📧 [mattia.verdolin@student.supsi.ch](mailto:mattia.verdolin@student.supsi.ch)
- **Francesco Fasola** 📧 [francesco.fasola@student.supsi.ch](mailto:francesco.fasola@student.supsi.ch)
- **Alessandro Cantoni** 📧 [alessandro.cantoni@student.supsi.ch](mailto:alessandro.cantoni@student.supsi.ch)

---

## 📜 License
This project was developed for educational purposes as part of the SUPSI Software Engineering 2 course. Redistribution or use outside the context of the course may require explicit permission from the authors.
