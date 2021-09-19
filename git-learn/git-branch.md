# Git branch 실습
### git branch, 왜 필요할까? - 통합 브랜치와 토픽 브랜치
1. 통합 브랜치는 언제든지 배포할 수 있는 버전을 만들 수 있어야 하는 브랜치(e.g. 모든 기능은 안정적으로 동작해야..)
2. 이 버전에 문제 혹은 추가 필요한 변경사항이 생겨 새로운 기능을 추가해야 할 때, '토픽 브랜치'를 만들 수 있음!
3. 여러 개의 추가 작업을 진행할 경우 그 수 만큼 토픽 브랜치 생성 가능
4. 토픽 브랜치에서 작업이 완료되면, 다시 통합 브랜치와 병합하여 기능을 완성해 나감

```bash
$ git init
$ touch README.md .gitignore # gitignore.io에서 복사
$ git commit -m "first commit" # 첫번째 커밋은 제일 먼저 하기!
$ git log 
>> commit 725ebd52cb2c7517c3240eb4f22518a62c71f41b (HEAD -> master)
```

1. branch (e.g. master)는 단순한 포인터(화살표)
2. HEAD는 단순한 포인터. (포인터의 포인터인 경우가 많음. Ex. HEAD -> master, HEAD -> 1번 commit 도 가능하나 이 경우 HEAD가 떨어져 나갔다고 하고 이를 알고 있어야 한다는 긴 메시지를 받게 된다.)
3. HEAD는 현재 내가(키보드를 치고 있는 나) 작업 중인 커밋을 의미한다.
4. HEAD가 master에 있다 == 현재 내가 master에서 작업 중이다.
5. `$ git branch <name>` 으로 새로운 브랜치 생성

```bash
$ git add .
$ git commit -m "2nd commit"
$ git log --oneline
# 새 커밋이 생기면서 HEAD와 master가 새 커밋을 가리킴
>> a3c3989 (HEAD -> master) 2nd commit  
>> 725ebd5 first commit
```

```bash
$ git branch b1   # master가 가리키고 있는 커밋에서 branch가 하나 생성됨
$ git log --oneline
# 나는 master branch에 있고 새로운 b1 branch가 2nd commit에서 생성됨
>> a3c3989 (HEAD -> master, b1) 2nd commit  
>> 725ebd5 first commit
# 내가 보는 branch를 b1으로 이동 시킴
$ git switch b1
$ git log --oneline
>> a3c3989 (HEAD -> b1, master) 2nd commit
>> 725ebd5 first commit
# 브랜치를 이동하였다 하더라도 commit 을 기준으로 작업 변경점이 분리되므로
# 분리점에서 꼭 commit을 할 것!
$ git add .
$ git commit -m '3rd commit'
$ git log --oneline
>> 2bbe779 (HEAD -> b1) 3rd commit
>> a3c3989 (master) 2nd commit
>> 725ebd5 first commit
$ touch secret.txt # 어떤 branch에서 commit 하느냐에 따라 소속이 달라짐
$ git add .
$ git commit -m '4rd commit'
$ git log --oneline
>> 8a02630 (HEAD -> b1) 4rd commit
>> 2bbe779 3rd commit
>> a3c3989 (master) 2nd commit
>> 725ebd5 first commit
# 현재 브랜치에서 부모를 찾아가면서 보여줌, 즉 master 브랜치에서는
# 3, 4번 commit 은 보이지 않게 됨!
```
### Git merge
#### Fast-forward 방식
1. master가 통합 브랜치이므로 master로 이동해서 합치자
2. b1이 개척해 놓은 길을 따라서 이동만 함
```bash
$ git add .
$ git commit -m "5th commit"
$ git switch master
$ git merge b1
>> Updating a3c3989..a56a8ab
>> Fast-forward
>> README.md  | 56 +++++++++++++++++++++++++++++++++++++++++++++++++++++++-
>> secret.txt |  0
>> 2 files changed, 55 insertions(+), 1 deletion(-)
>> create mode 100644 secret.txt

$ git log --oneline
>> a56a8ab (HEAD -> master, b1) 5th commit
>> 8a02630 4rd commit
>> 2bbe779 3rd commit
>> a3c3989 2nd commit
>> 725ebd5 first commit
$ git add .
$ git commit -m '6th commit'
```
#### Auto Merge & Manual merge 방식
#### master에서 작업을 하게 된다면??!!

```bash
$ touch master.txt
$ git log --oneline
>> 4687fee (HEAD -> master) 9th commit
>> c5d70d7 6th commit
>> a56a8ab (b1) 5th commit
>> 8a02630 4rd commit
>> 2bbe779 3rd commit
>> a3c3989 2nd commit
>> 725ebd5 first commit
$ git add .
$ git commit -m '10th commit'
# b2에서 작업한 내용
$ git branch b2     # 모든 branch 보기: $ git branch
$ git switch b2
$ git log --oneline
$ touch b2.txt    # b2.txt 수정
$ git add .
$ git commit -m '7th commit'
$ git add . 
$ git commit -m '8th commit'

# branch merge, master branch 에서 b2에서 작업한 내용을 합치자
$ git merge b2  # 줄에 겹친 내용 때문에 conflict가 발생하고, 추가 적용하면 둘다 나옴 -> 이상태에서 다시 git add. // git commit 을 해서 auto merge의 경우 commit을 동반하게 됨!
# 즉 Auto merge를 하려했으나.. 실제로는 conflict merge를 하게됨!
$ git add . 
$ git commit -m '11th commit auto merge'
$ git add . 
$ git commit -m '12th commit'

# git branch 생성하면서 바로 switch 하기!
$ git switch -c b3
# b3 branch
$ touch b3.txt
$ git add . 
$ git commit -m '13th commit'
$ git switch master
$ git add .
$ git commit -m '14th commit'
# secret.txt에 겹치도록 작성
$ git add .
$ git commit -m '15th commit'
$ git merge b3
# conflict merge 처리하고
$ git add .
$ git commit -m '16th commit : conflict merge'
$ git add .
$ git commit -m '17th commit'
```