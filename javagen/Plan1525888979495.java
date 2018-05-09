public class Plan1525888979495 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

}

IncreaseDimmer("A");


DecreaseTraffic("A");

}

}
}
