// MyAIDL.aidl
package com.review.aidl.client;
import com.review.aidl.client.bean.Person; //引入
interface MyAIDL {
       String getInfor(String s);
       /*
       * 参数上加入方向指示符in，代表参数由客户端设置，我们还需要为Person提供一个import语句(虽然说在同一个包下).
       */
       String greet(in Person person);
}
