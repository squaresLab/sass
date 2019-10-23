public class Plan1571773295084 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("A");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



if ( StartServer("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
IncreaseDimmer("B");
}

StartServer("B");


} else {
StartServer("C");
}

}

}
}
