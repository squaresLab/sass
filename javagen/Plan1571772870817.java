public class Plan1571772870817 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
} else {

}

StartServer("B");
DecreaseTraffic("A");


}



}
}
