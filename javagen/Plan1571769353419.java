public class Plan1571769353419 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

} else {

}


}

}
}
