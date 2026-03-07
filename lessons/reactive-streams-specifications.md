# Reactive Streams Specification

## Key Interfaces
- **Publisher** → produces events
- **Subscriber** → consumes events (`onSubscribe`, `onNext`, `onError`, `onComplete`)
- **Subscription** → manages relationship (`request`, `cancel`)
- **Processor** → acts as both publisher and subscriber

## Workflow
1. Subscriber calls `subscribe()` on Publisher
2. Publisher sends Subscription event
3. Subscriber requests data (`request(n)`)
4. Publisher sends data via `onNext` (n times)
5. Completion via `onComplete` or error via `onError`
