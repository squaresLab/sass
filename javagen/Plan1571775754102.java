public class Plan1571775754102 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("B");
}


}

DecreaseTraffic("A");

}

}
}
