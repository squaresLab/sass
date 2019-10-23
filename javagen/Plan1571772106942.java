public class Plan1571772106942 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("C");

DecreaseTraffic("A");
StartServer("C");


for (int i = 0; i < 2 ; i++) {
StartServer("B");
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


StartServer("C");
StartServer("A");


if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
DecreaseDimmer("C");
}


}


}
}
