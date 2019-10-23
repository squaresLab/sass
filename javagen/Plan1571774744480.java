public class Plan1571774744480 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if (  ) {
ShutdownServer("B");
} else {
if ( StartServer("B") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("B");
StartServer("A");

}

StartServer("B");

}

StartServer("A");
StartServer("C");
DecreaseTraffic("A");


StartServer("C");


}

}
}
