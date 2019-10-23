public class Plan1571769077785 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
StartServer("B");
StartServer("C");
StartServer("B");


StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}



for (int i = 0; i < 6 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

}



}
}
