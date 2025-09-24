# Chat App (Java AWT)

A minimal two-window chat application built with Java AWT using sockets. One window acts as the server (Tharun) and the other as the client (Client). Messages are exchanged over localhost on port 1234.

## Features
- **Simple UI**: AWT `Frame`, `TextArea`, `TextField`, and `Button`
- **Realtime chat**: Bi-directional messaging over TCP sockets
- **Two roles**: `Tharun` (server) and `Client` (client)

## Tech Stack
- Java (AWT)
- Sockets (`java.net.ServerSocket`, `java.net.Socket`)

## Project Structure
```
app/
├─ src/
│  └─ src/
│     ├─ Client.java       # Client UI and socket client (connects to localhost:1234)
│     └─ Tharun.java       # Server UI and socket server (listens on :1234)
├─ out/                    # Compiled classes (if built)
└─ README.md
```

## Requirements
- Java JDK 8+ (11+ recommended)
- Windows, macOS, or Linux

## How it Works
- `Tharun` starts a `ServerSocket` on port `1234` and waits for a connection.
- `Client` connects to `localhost:1234`.
- Both sides use `DataInputStream`/`DataOutputStream` to exchange UTF-8 strings on a background thread while the UI stays responsive.

## Running (Command Line)
Open a terminal in the project root (`app/`).

1) Compile
```bash
javac -d out src/src/*.java
```

2) Start the server window (Tharun)
```bash
java -cp out src.Tharun
```

3) Start the client window (Client) in a second terminal
```bash
java -cp out src.Client
```

You should now have two windows. Typing a message in one window and pressing "Send" will display it in the other.

## Running (IntelliJ IDEA)
- Open the project (it already includes `.idea` metadata).
- Mark `src/src` as a Source Root if not already.
- Create two Run Configurations with main classes:
  - `src.Tharun`
  - `src.Client`
- Run `Tharun` first, then `Client`.

## Configuration
- Host: `localhost`
- Port: `1234`

To change the port/host, update the constructor code in `Tharun.java` (server) and `Client.java` (client) accordingly.

## Troubleshooting
- "Connection refused": Ensure `Tharun` (server) is running before starting `Client`.
- Port conflicts: If port `1234` is in use, change to a free port in both files.
- Firewall prompts: Allow Java to accept connections on `localhost`.
- Blank or non-updating UI: Make sure both windows are running; check the terminal for exceptions.

## Notes & Limitations
- Single connection only (one client).
- Plain-text messages; no encryption/authentication.
- Basic AWT UI; no message history persistence.

## Next Steps (Ideas)
- Support multiple clients with a broadcast server.
- Replace AWT with Swing or JavaFX for richer UI.
- Add timestamps, message history, and scroll improvements.
- Add configurable host/port via UI or args.

