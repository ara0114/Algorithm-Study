def solution(s):
    answer = []
    zerocnt = 0
    recnt = 0
    
    while s != '1':
        lcnt = 0;
        for i in range(0, len(s)):
            if s[i] == '0' :
                zerocnt+=1
            else:
                lcnt+=1
        s = bin(lcnt)[2:]
        recnt+=1
    
    answer.append(recnt)
    answer.append(zerocnt)
    return answer