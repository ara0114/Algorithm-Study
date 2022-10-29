def check(str):
    stack = []
    for i in range(0, len(str)):
        c = str[i]
        if c == '(' or c == '[' or c == '{' :
            stack.append(c)
        else:
            if len(stack) == 0:
                return False
            else:
                p = stack.pop()
                if c == ')' and p == '(':
                    continue
                elif c == ']' and p == '[':
                    continue
                elif c == '}' and p == '{':
                    continue
                else:
                    return False
    if len(stack) != 0:
        return False
    else:
        return True
    
def solution(s):
    answer = 0
    for i in range(0, len(s)):
        s = s[1:] + s[0];
        if check(s):
            answer += 1
    return answer

