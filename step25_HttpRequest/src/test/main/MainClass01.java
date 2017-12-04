package test.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainClass01 {
	public static void main(String[] args){
		String requestUrl="http://study.kimgura.net/members";
		
		URL url=null;
		HttpURLConnection conn=null;
		
		try {
			url=new URL(requestUrl);
			conn=(HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(10000);
            conn.setDoOutput(true); //데이터를 전송할수 있도록
            conn.setRequestMethod("GET"); //post 방식으로 전송하기 위해
            conn.setDefaultUseCaches(false);
            //서버에 연결되었을때 응답 코드 읽어오기 
            int responseCode=conn.getResponseCode(); //반듯이 써줘야한다.
            //서버에서 출력하는 문자열을 누적시킬 객체
            StringBuilder builder=null;
            //만일 서버가 성공적으로 응답했다면.. 
            if(responseCode==HttpURLConnection.HTTP_OK){
                builder=new StringBuilder(); //객체 생성
                //InputStreamReader 객체 얻어오기
                InputStreamReader isr=
                        new InputStreamReader(conn.getInputStream());
                BufferedReader br=new BufferedReader(isr);
                //반복문 돌면서 읽어오기
                while(true){
                    String line=br.readLine();
                    if(line==null)break;
                    //읽어온 문자열을 객체에 저장
                    builder.append(line);
                }
                isr.close();
                br.close();
            }
            //응답된 모든 문자열을 String type 으로 얻어내기 
            String resultMsg=builder.toString();
            //문자열 출력하기 
            System.out.println(resultMsg);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.disconnect();
		}
	}

}
