def solution(n):
    answer = 0
    dp = [0 for i in range(n+1)]
     
    if n <= 2:
        return n;
    
    dp[1] = 1
    dp[2] = 2
    
    for i in range(3, n+1):
        dp[i] = (dp[i-2] + dp[i-1]) % 1234567;
    
    answer = dp[n]
    return answer