# 병합 정렬
# 왼쪽과 오른쪽으로 길이가 1이 될 때까지 나누기
# 길이가 1이되서 반환된 리스트끼리 병합을 하며 왼쪽과 오른쪽 비교하며 정렬
# 합친 행렬을 병합하여 반환되면 다시 왼쪽과 오른쪽을 비교하며 정렬해나가는 방식
arr = [5, 3, 2, 1, 10, 100, 65, 23]

def merge(left, right):
    mg = []
    i = j = 0
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            mg.append(left[i])
            i+=1
        else:
            mg.append(right[j])
            j+=1
    
    mg.extend(left[i:])
    mg.extend(right[j:])
    return mg

def merge_sort(arr):
    if len(arr) == 1:
        return arr
    mid = len(arr)//2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    print('left:', left)
    print('right:', right)
    return merge(left, right)

print(merge_sort(arr))

