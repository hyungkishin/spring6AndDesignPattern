### RestTemplate 의 두가지 전략
- HTTP API 요청을 처리하는 템플릿
  - HTTP Client 라이브러리 확장: ClientHttpRequestFactory
  - Message Body 를 변환하는 전략: HttpMessageConverter

### ClientHttpRequestFactory
HTTP Client 기술을 사용해서 ClientHttpRequest 를 생성하는 전략
- SimpleClientHttpRequest (HttpURLConnection)
- JdkClientHttpRequest (HttpClient)
- NettyClientRequest
- JettyClientRequest
- OkHttp3ClientRequest

### doExecute()
HTTP API 호출 workflow 를 가지고 있는 템플릿 메소드로 두 대의 콜백을 받음
- RequestCallback
- void doWithRequest