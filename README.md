# seraph

A Terminal Client originally for **AngelOS** (WIP). Open-sourced and free to modify.

**Current Version:** V0.1.11b

---

## 📝 Changelog

### V0.1.11b
* Changed from manual Scanner lib to JLine lib.
* Added `run.sh` for easier execution of code.

### V0.1.11a
* Added placeholder `sph` (package manager) command to be updated later.
* Added base commands: `echo`, `help`, `clear`, and `logout`.

---

## 🚀 How to Run `run.sh`

Choose the instructions matching your operating system.

### 🪟 Windows (PowerShell)
PowerShell cannot run shell scripts natively. Use one of these tools:

#### Option 1: Git Bash (Recommended)
```powershell
# If Git is in your Environment PATH:
bash .\run.sh

# If the shortcut above fails, use the direct path:
& "C:\Program Files\Git\bin\bash.exe" .\run.sh
```

#### Option 2: Windows Subsystem for Linux (WSL)
```powershell
wsl bash ./run.sh
```

> 💡 **Troubleshooting Windows Errors:**
> * **"Permission Denied"**: Fix it by running `wsl chmod +x ./run.sh`
> * **Syntax/`\r` Errors**: Windows text formats break Linux scripts. Open `run.sh` in VS Code or Notepad++ and change the line endings from **CRLF** to **LF**, then save.

---

### 🍏 macOS
macOS handles shell scripts natively via the Terminal app.

1. Open **Terminal** and navigate to your project folder:
   ```bash
   cd /path/to/seraph-folder
   ```
2. Grant the file execution permission (Required only the first time):
   ```bash
   chmod +x run.sh
   ```
3. Execute the script:
   ```bash
   ./run.sh
   ```

---

### 🐧 Linux
Linux systems offer two distinct methods for running the script.

#### Method 1: Standard Execution (Recommended)
Runs the script safely in an isolated temporary subshell.
```bash
chmod +x run.sh
./run.sh
```

#### Method 2: Sourcing the Script
Runs the script directly inside your active terminal session.
```bash
source run.sh
# OR use the shortcut:
. ./run.sh
```

#### ⚠️ Critical Difference Matrix

| Feature | Standard (`./run.sh`) | Source (`source run.sh`) |
| :--- | :--- | :--- |
| **Needs `chmod +x`?** | **Yes** | **No** (Only needs read access) |
| **Environment Changes** | Kept temporary | Saved permanently to current terminal |
| **If script calls `exit`** | Closes the script safely | **Closes your entire terminal window** |
