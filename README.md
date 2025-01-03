# APM Java Agent
This project is a lightweight Application Performance Monitoring (APM) Java agent built using Byte Buddy for tracking method calls, execution times, and nested call hierarchies in Java applications.

## Project Structure
- **BasicAgent.java:** ->  *Sets up the agent and attaches instrumentation logic.*
- **MethodAdvice.java:** ->  *Defines logic to capture method entry, exit, and execution details.*
- **CallContext.java:**  -> *Tracks method call hierarchy using thread-local storage.*
- **FunctionCallRecord.java:** ->  *Data structure to store method call details.*
- **FunctionLogger.java:**  -> *Handles logging and metrics aggregation.*
- **TestApp.java:** Sample  -> *application to test and demonstrate the APM agent.*

## Run Command
```
java -javaagent:{APMJavaAgent-1.0-SNAPSHOT-shaded.jar File Address} -cp {Test Java App File Address}
```
### Example:

```
java -javaagent:C:\Users\rajen\IdeaProjects\APMJavaAgent\target\APMJavaAgent-1.0-SNAPSHOT-shaded.jar -cp C:\Users\rajen\IdeaProjects\TestJavaApp\target\classes org.example.TestApp
```

## How It Works
1. The agent intercepts method calls in target classes (org.example package).
2. Captures method execution details (start time, duration, exceptions).
3. Logs method call hierarchy with parent-child relationships.
4. Outputs the captured data in a human-readable format.

## Output
![image](https://github.com/user-attachments/assets/54af5fd5-4e0a-4fa8-a90e-06e1cff3504d)

