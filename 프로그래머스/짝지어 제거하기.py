def solution(s):
    answer = -1
    
    if len(s) % 2 != 0:
        return 0
    
    stack = []
    
    for i in range(0, len(s)):
        c = s[i]
        if len(stack) != 0 and stack[-1] == c:
            stack.pop()
        else:
            stack.append(c)
    
    if len(stack) == 0:
        answer = 1
    else:
        answer = 0
    return answer