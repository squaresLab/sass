public class Plan1571775164061 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

}


StartServer("A");

DecreaseTraffic("A");

} else {
DecreaseDimmer("A");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

}

DecreaseTraffic("A");


}

}

}
}
