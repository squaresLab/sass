public class Plan1571768743564 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
StartServer("B");
if ( StartServer("A") ) {
StartServer("C");
} else {
DecreaseTraffic("A");
}


}

}
}
