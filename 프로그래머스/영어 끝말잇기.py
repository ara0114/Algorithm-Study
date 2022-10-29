def solution(n, words):
    answer = [0, 0]
    dup = []
    dup.append(words[0])
    
    for i in range(1, len(words)):
        cur = words[i]        
        prev = words[i-1]
        
        last = prev[-1]
        first = cur[0]
            
        if cur in dup or last != first:
            answer = [i%n+1, i//n+1]
            break
        
        dup.append(cur)
    
    return answer