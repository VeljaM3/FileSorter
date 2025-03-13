# **📂 File Sorter - Organize Files Based on Size**  
📌 A Java application that automatically sorts files into folders based on their size.

### 📝 Project Overview  
This project is a simple Java Swing application that organizes files in a given folder into three categories:

📁 Large Files – (> 100 MB)  
📁 Medium Files – (10 MB - 100 MB)  
📁 Small Files – (≤ 10 MB)  

The application creates the necessary folders and moves files accordingly, helping users keep their directories well-organized.

### 🚀 Features  
✔️ Automatic File Organization – Files are sorted into predefined size-based categories.  
✔️ Graphical User Interface (GUI) – Simple and interactive interface built with Swing.  
✔️ Real-Time Feedback – Users can see file movements and status updates in the GUI.  
✔️ Cross-Platform – Works on Windows, macOS, and Linux (minor path modifications may be needed).  

### 🛠 Technologies Used  
Java 17 – Core programming language  
Swing (javax.swing) – GUI framework for user interaction  
Java NIO (java.nio.file) – File handling and directory management  
Maven – Dependency management and project build tool  

### 🎯 How It Works?  
1️⃣ Launch the application via the GUI (OrganizeFilesGUI.java).  
2️⃣ A welcome screen appears, providing basic instructions.  
3️⃣ Click "Start" to navigate to the main screen.  
4️⃣ Click "Run" to trigger the file sorting process.  
5️⃣ The application will:
- List all files and their sizes.
- Create the necessary directories (LargeFiles, MediumFiles, SmallFiles).
- Move files into their respective folders.

6️⃣ Users will see a status message confirming the operation's success.
