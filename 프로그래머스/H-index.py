def solution(citations):
    answer = 0
    citations.sort()
    
    for i in range(0, len(citations)):
        h = len(citations) - i
        if citations[i] >= h:
            answer = h
            break
    return answer