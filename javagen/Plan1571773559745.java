public class Plan1571773559745 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
StartServer("B");

}


}

StartServer("B");
if ( StartServer("A") ) {
StartServer("B");
StartServer("C");

DecreaseDimmer("B");

} else {
StartServer("C");
}



StartServer("B");

StartServer("C");

}
}
