public class Plan1571770320385 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
DecreaseTraffic("A");
StartServer("C");
StartServer("B");


StartServer("C");


if ( StartServer("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
}


}

}
}
