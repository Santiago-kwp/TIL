# Hoare Partition
def partition(arr, l, r):
    piv = arr[l]
    i, j = l, r
    while i <= j:
        # 피벗을 기준으로 왼쪽은 피벗보다 작거나 같은 값일 때까지 
        # 오른쪽은 피벗보다 크거나 같은 값의 모임
        while i <= j and arr[i] <= piv: i+=1
        while i <= j and arr[j] >= piv: j-=1
        # 아직 파티셔닝이 남은 경우 위치 추가로 바꿔줌
        if i < j: arr[i], arr[j] = arr[j], arr[i] 
    arr[l], arr[j] = arr[j], arr[l]
    # 피벗의 위치 리턴
    return j

# quick sort using recursion
def quick_sort_rc(arr):
    if len(arr) <= 1:
        return arr
    
    piv = arr[0]
    rest = arr[1:]
    
    left = [x for x in rest if x <= piv]
    right = [x for x in rest if x > piv]
    return quick_sort_rc(left) + [piv] + quick_sort_rc(right)

def quick_sort(arr, l, r):
    if l < r:
        s = partition(arr, l, r)
        quick_sort(arr, l, s-1)
        quick_sort(arr, s+1, r)
    pass

arr = [2, 2, 1, 1, 3]
quick_sort(arr,0, len(arr)-1)
print(arr)

print(quick_sort_rc(arr))