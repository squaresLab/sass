public class Plan1525888986339 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("A");
StartServer("C");


StartServer("B");

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

DecreaseDimmer("A");

}

}
}
