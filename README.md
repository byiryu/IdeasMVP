# Ideas Blind Application Challenge
  프로그래머스 iOS/Android 개발자 채용 챌린지
  
# Preview
![4](https://user-images.githubusercontent.com/6897464/70773046-a99cc400-1db9-11ea-8fac-6e3f695f7c3c.png)


# Tech

메인
>  Product List 로드는 스크롤이 바닥에 닿은후 1.5초 로드한다.


상세 
>  Pruduct Deatil Image 로드는 0.5초후 로드된다.  
   Proudct Detail Buy 버튼로드는 0.5초후 로드된다.   

문제점
> Product Detail ImageAdapter가 Notfiy되는 시점에 화면이 깜박거리는 현상. 
여러 자료를 찾아보았지만 아직 해결이 안됨.

More 
> **RecyclerView** 에서 **SharedElementTransition**를 이용해 상세페이지 애니메이션구현  
상세페이지  **SheardElementTransition**과 **ImageAdapter**의 연결고리?
  
# Log..
 
  Category  : Android / Kotlin     
  DI        : dagger2             https://github.com/google/dagger  
  API       : Retrofit            https://github.com/square/retrofit  
  Image     : Glide               https://github.com/bumptech/glide  
  
  구글 아키텍처 디자인패터은 MVP와 MVVM를 고민하였다. 익숙한 MVP 보단 MVVM을 공부하고 있었고 시작은 MVVM으로 했다.
  하지만 공부를 덜 한것인지. 역시 익숙한 MVP로 돌아갔다.
  
  애니메이션 구현에 있어서 어려움을 많이 겼었다.
 **ShaedElmentTransition** 을 사용하여 메인->상세 뷰의 애니메이션 효과를 지정하였고, 
  구매하기 버튼 애니메이션을 style과 anim resouce로 구현하려 했으나 잘 동작하지 않아 함수로 구현하였다.
  
  
  
  
  
