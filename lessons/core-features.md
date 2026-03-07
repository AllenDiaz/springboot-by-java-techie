# Core Features of Reactive Programming

## 1. Asynchronous & Non-blocking
- **Analogy**: Restaurant order handling  
  - Synchronous: Owner waits for cook to finish one order before taking the next.  
  - Asynchronous: Owner takes multiple orders and serves them as soon as the cook notifies that food is ready.  
- **Thread-per-request vs Event-loop**  
  - Traditional REST APIs block threads while waiting for responses.  
  - Reactive programming frees threads by using event loops.  
- **Advantages**  
  - Scalability  
  - Better CPU utilization  
  - Reduced downtime  

---

## 2. Functional Style Code
- **Comparison with Traditional REST API**  
  - Imperative style: `deleteUser(id)` → directly calls DB.  
  - Reactive style: Uses `Mono` and `Flux` with functional operators (`flatMap`, `map`).  
- **Key Data Types**  
  - `Mono` → handles a single object  
  - `Flux` → handles multiple objects (0 to N)  
- **Similarity**  
  - Syntax and operators resemble Java Stream API.  

---

## 3. Event-driven Data Flow
- **Publisher–Subscriber Model**  
  - Publisher emits events.  
  - Subscriber consumes events in real time.  
- **Continuous Streaming of Updates**  
  - Example: Cricket live score updates pushed automatically.  
- **Always-open Connections**  
  - Enables real-time updates without repeated API calls.  

---

## 4. Back Pressure
- **Handling Large Data Streams**  
  - Prevents overload when consumer cannot process fast enough.  
- **Slowing Down Data Flow**  
  - Subscriber can request data at a manageable pace (`request(n)`).  
- **Benefits**  
  - Prevents crashes  
  - Avoids memory overload  
  - Ensures stable performance under heavy load  

---

## ✅ Summary
Reactive Programming’s core features provide:
- High concurrency with fewer threads  
- Declarative, functional-style coding  
- Real-time event-driven updates  
- Safe handling of large data streams with back pressure
