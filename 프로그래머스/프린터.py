import heapq
def solution(priorities, location):
    answer = 0
    heap = []
    for num in priorities:
        heapq.heappush(heap, -num)
    while heap:
        for i in range(len(priorities)):    
            if -heap[0] == priorities[i]:
                heapq.heappop(heap)
                answer += 1
                if i == location:
                    return answer
    return answer