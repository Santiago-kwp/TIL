# git 내용정리

## Git 개념

> git은 컴퓨터 파일의 변경사항을 추적하고 여러 명의 사용자들 간에 해당 파일들의 작업을 조율하기 위한 분산 버전 관리 시스템이다. 코드의 History를 관리하는 도구로 개발된 과정과 역사를 볼수 있고,  중앙 서버에서 파일을 받아서 사용(Checkout)하는 것이 아니기 때문에 중앙 서버에 문제가 생겨도 클라이언트 중에서 아무거나 골라 서버를 복원할 수 있다. 



## Git 특징

> 작업물 간의 차이가 무엇이고, 수정 이유를 log로 남길 수 있다.
>
> 현재 파일들을 안전한 상태로 과거 모습 그대로 복원 가능(반대도 마찬가지)
>
> 버전 사이의 이동이 용이하고, 각 버전별로 차이점만 저장해서 사이즈 감소

- 뼈대 코드 구성
- 메인 기능 구현
- 로그인 기능 구현
- 채팅 기능 구현
- 디자인 적용



## Git 작업 흐름

- Working directory 내 작업(수정)한 파일을 `add` 하여 `commit`할 목록에 추가
- `commit`(create a snapshot) 만들기
- `push` 하여 현재까지의 역사(commits)가 기록되어 있는 곳에 새로 생성한 커밋들 반영하기



## Git 설정

### 전역 영역에서 commit 기록의 주인을 등록

```bash
$ git config --global user.name "username"
$ git config --global user.email "edu@hphr.kr"
```



## Git 기본

- `git init` : 해당 디렉토리를 Git이 관리하도록 초기화

- `add 파일명 ` : 커밋할 목록에 추가
- `commit -m "커밋 메시지"`(히스토리의 한단위) 만들기
- `git push origin master` : 현재까지의 역사(commits)가 기록되어 있는 곳에 새로 생성한 커밋 반영



## Git 저장소

- **로컬(working directory)** > **staging area** > **remote repository(github, bitbucket, gitlab)**
- 로컬 컴퓨터 저장소 해당 버전의 스냅샷(기록), 원격 저장소



## Git branch

> 같은 작업물을 기반으로 동시에 작업을 할 수 있게 만들어주는 기능

- 독립적인 작업 영역 안에서 마음대로 소스코드를 변경할 수 있다

- 분리된 작업 영역에서 변경된 내용은 추후에 기존 버전과 비교해서 새로운 하나의 버전을 만들어 낼 수 있다.

  

  