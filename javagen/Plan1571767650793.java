public class Plan1571767650793 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 5 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}

StartServer("A");


StartServer("A");

}
}
