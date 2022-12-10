# Deque

- Double Ended Queue. 큐의 양쪽에서 삽입과 삭제를 수행할 수 있음
- 큐와 스택을 합쳐 놓은 형태
- Deque의 앞에서 데이터 넣고 뒤쪽에서 빼면 큐, 앞에서 넣고 앞에서 빼면 스택 처럼 사용가능
- 양쪽에서 입출력 모두 가능하지만, 한쪽으로만 입력 가능하도록 설정하면 스크롤, 한쪽으로만 출력 가능하도록 설정하면 셸프라고 함

## 생성

- ArrayDeque, LinkedBlokingDeque, ConcurrentLinkedDeque, LinkedList를 이용해 생성가능

  ```java
  Deque<> deque = new ArrayDeque<>();
  Deque<> deque = new LinkedBlokingDeque<>();
  Deque<> deque = new ConcurrentLinkedDeque();
  Deque<> deque = new LinkedList<>();
  ```

## 삽입

- 앞쪽(왼쪽)에서 삽입
  - addFirst() : 맨 앞에 원소 삽입, 용량 초과시 예외발생
  - offerFirst() : 맨 앞에 원소 삽입, 성공시 true, 용량 초과시 false

- 뒤쪽(오른쪽)에서 삽입
  - addLast() : 마지막에 원소 삽입, 용량 초과시 예외발생
  - add() : 마지막에 원소삽입, 용량 초과시 예외발생
  - offerLast() : 마지막에 원소 삽입, 성공시 true, 용량 초과시 false
  - offer() : 마지막에 원소 삽입, 성공시 true, 용량 초과시 false
- addFirst()는 push(), removeFirst()는 pop()메소드와 동일 => 스택으로 사용할때 

## 삭제

- 앞쪽(왼쪽)에서 삭제

  - remove() : 맨 앞에 원소 제거 후, 해당 원소 리턴. 비어있을 경우 예외발생

  - removeFirst() : 맨 앞에 원소 제거 후, 해당 원소 리턴. 비어있을 경우 예외발생

  - poll() : 맨 앞에 원소 제거 후, 해당 원소 리턴. 비어있을 경우 null리턴

  - pollFirst() : 맨 앞에 원소 제거 후, 해당 원소 리턴. 비어있을 경우 null리턴

- 뒤쪽(오른쪽)에서 삭제
  - removeLast() : 마지막 원소 제거 후, 해당 원소 리턴. 비어있을 경우 예외발생
  - pollLast() : 마지막 원소 제거 후, 해당 원소 리턴. 비어있을 경우 null리턴

- 데이터를 찾아서 삭제
  - removeFirstOccurrence(Object o) : deque의 앞쪽에서 인자와 같은 첫 데이터 찾아 제거. 없으면 아무일도 안일어남
  - removeLastOccurrence(Object o) : deque의 뒤쪽에서 인자와 같은 첫 데이터 찾아 제거. 없으면 아무일도 안일어남

## 조회

- 앞쪽
  - getFrist() : 맨 앞의 원소 리턴. 비어있을 경우 예외발생
  - peek() : 맨 앞의 원소 리턴. 비어있을 경우 null리턴
  - peekFirst() : 맨 앞의 원소 리턴. 비어있을 경우 null리턴
- 뒤쪽
  - getLast() : 마지막 원소 리턴. 비어있을 경우 예외발생
  - peekLast() : 마지막 원소 리턴. 비어있을 경우 null리턴

- 특정 데이터 확인
  - contain(Object o)
- deque에 존재하는 엘리먼트 갯수 확인
  - size()