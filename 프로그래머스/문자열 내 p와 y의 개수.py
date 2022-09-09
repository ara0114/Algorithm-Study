def solution(s):
    answer = True
    s1 = s.lower()
    pcount = 0
    ycount = 0

    for i in s1:
        if i == 'p':
            pcount += 1
        elif i == 'y':
            ycount += 1

    if pcount == ycount:
        answer = True
    else:
        answer = False

    return answer
