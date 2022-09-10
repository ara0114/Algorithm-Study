def solution(x):
    answer = True
    sum = 0
    s = str(x)

    for i in s:
        sum += int(i)

    if x % sum == 0:
        answer = True
    else:
        answer = False

    return answer
