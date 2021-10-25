import sys
sys.stdin = open('prim_sample.txt')

T = int(input()) # Number of TC cases

def prim(V, start): # V: 노드 수, 시작 정점
    key[start] = 0  # 시작 정점의 가중치(key)
    MST = [0]*(V+1) # MST : 방문 체크 리스트

    for _ in range(V):              # 모든 정점 순회
        u = 0                       # 선택할 정점 초기화
        minV = INF                  # 선택할 가중치 초기화
        for i in range(1, V+1):     # 현재 정점에서 연결된 곳 중 최소 가중치의 정점 찾기
            if not MST[i]:
                if key[i] < minV:
                    u = i
                    minV = key[i]           
        MST[u] = 1                  # 최소 신장트리에 가중치가 최소인 u 정점 방문
        for v in range(V+1):
            if not MST[v] and adj[u][v]:    # 최소신장트리에 포함되지 않은 정점 중 인접한 정점에서
                if key[v] > adj[u][v]:      # 인접한 정점과 가중치가 현재 정점의 가중치보다 작으면 갱신
                    key[v] = adj[u][v]
    return sum(key)



for tc in range(1, T+1):
    V, E = map(int, input().split())        # 정점 개수 V, E: 간선 수
    INF  = 2000000                          # 엄청 큰 값
    key  = [INF] * (V+1)                    # 각 정점 간 가중치를 INF로 초기화
    adj  = [[0]*(V+1) for _ in range(V+1)]  # 인접한 정점 표시할 2d 리스트 정의

    for _ in range(E):
        n1, n2, w = map(int, input().split())   # 각 정점 번호 n1, n2, 가중치 w
        adj[n1][n2] = w                         # 무방향 그래프
        adj[n2][n1] = w

    print('#{} {}'.format(tc, prim(V, 0)))

