# Core Features of Reactive Programming

Reactive Programming introduces several powerful features that make applications more scalable, efficient, and responsive. Below are the key concepts explained with examples.

---

## 1. Asynchronous & Non-blocking
- **Traditional (Synchronous)**: Each request blocks a thread until completion.  
  Example: A restaurant owner waits for the cook to finish one order before taking the next.  
- **Reactive (Asynchronous)**: Requests are processed concurrently without blocking threads.  
  Example: The restaurant owner takes multiple orders and serves them as soon as the cook notifies that food is ready.  

**Advantages:**
- Handle more requests with fewer resources  
- Improved CPU utilization  
- Reduced waiting time and downtime  

---

## 2. Functional Style Code
- Reactive code uses **functional programming constructs** similar to Java Stream API.  
- Key data types:
  - **Mono** → handles a single object  
  - **Flux** → handles 0 to N objects  

**Example:**
```java
Mono.just("JavaTechie")
Flux.just("Spring", "Hibernate", "Microservices")
