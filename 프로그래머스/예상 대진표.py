def solution(n,a,b):
    answer = 0

    if n == 2:
        return 1
    while(True):
        a = a // 2 + a % 2
        b = b // 2 + b % 2
        
        answer += 1
        
        if a == b:
            break
    
    return answer