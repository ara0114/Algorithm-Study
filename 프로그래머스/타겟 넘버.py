answer = 0
def dfs(numbers, target, depth, sum):
    if depth == len(numbers):
        if sum == target:
            global answer
            answer += 1
                
    else:
        dfs(numbers, target, depth + 1, sum + numbers[depth])
        dfs(numbers, target, depth + 1, sum - numbers[depth])
        
def solution(numbers, target):
    dfs(numbers, target, 0, 0)
    return answer