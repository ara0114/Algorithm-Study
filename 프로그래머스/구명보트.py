def solution(people, limit):
    answer = 0
    
    people.sort()
    
    minidx = 0
    maxidx = len(people)-1
    
    while minidx <= maxidx:
        
        if people[maxidx] + people[minidx] <= limit:
            minidx += 1
        
        answer+=1
        maxidx-=1
            
    return answer