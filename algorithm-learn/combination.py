# 조합을 생성해보자
# 조합 점화식 활용
# nCr = n-1Cr-1 + n-1Cr
arr = [1, 2, 3, 4]
N = 4
visited = [0]*N
def combination(i, N, r):
    global cnt
    if r == 0:
        cnt += 1
        print('#{}:'.format(cnt), end=' ')
        for i in range(N):
            if visited[i]:
                print(arr[i], end=' ')
        print()
        return
    if i == N: return

    # 특정 원소를 포함하고 뽑는 경우, 안포함하고 뽑는 경우
    for c in [1, 0]:
        visited[i] = c
        combination(i+1, N, r-c)

for i in range(1,N+1):
    print('{}개 중에 {}개 뽑는 경우의 수'.format(N, i))
    cnt = 0
    combination(0,4,i)

            
