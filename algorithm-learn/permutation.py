# 재귀문을 통한 순열 생성, 사전 순서는 아님
# 4개의 원소를 순서 있게 나열하면 경우의 수는 4! = 24
arr = [1, 2, 3, 4]
cnt = 0
def perm(i, N):
    global cnt
    if i == N:
        cnt += 1
        print('#{} {}'.format(cnt, arr))
        return
    for j in range(i, N):
        arr[i], arr[j] = arr[j], arr[i]
        perm(i+1,N)
        arr[i], arr[j] = arr[j], arr[i]
# perm(0, len(arr))

# nPr : n 개중 r 개만 뽑아서 나열하는 경우
def nPr(i, N, r):
    global cnt
    if i == r:
        cnt += 1
        print('#{} {}'.format(cnt, arr[:r]))
        return
    for j in range(i, N):
        arr[i], arr[j] = arr[j], arr[i]
        nPr(i+1, N, r)        
        arr[i], arr[j] = arr[j], arr[i]

# nPr(0, 4, 2)

#사전 순서로 순열을 생성을 해보자!
N = 4
def right_rotate(s,e):
    last = arr[e]
    for i in range(e,s-1,-1):
        arr[i] = arr[i-1]
    arr[s] = last

def left_rotate(s, e):
    first = arr[s]
    for i in range(s,e):
        arr[i] = arr[i+1]
    arr[e] = first

def sort_perm(i, N):
    global cnt
    if i == N:
        cnt += 1
        print('#{} {}'.format(cnt, arr))
        return

    for j in range(i, N):
        right_rotate(i,j)
        sort_perm(i+1, N)
        left_rotate(i, j)

sort_perm(0, N)
    
