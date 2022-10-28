def solution(brown, yellow):
    answer = []
    area = brown + yellow
    
    for i in range(1, area+1) :
        height = i;
        if area % i == 0:
            width = area//i
            
        if width >= height:
            if (width-2)*(height-2) == yellow :
                answer.append(width)
                answer.append(height)
            else :
                continue

    return answer