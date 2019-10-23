public class Plan1571769053954 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("C");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


} else {
DecreaseDimmer("A");
if ( StartServer("B") ) {
StartServer("A");
} else {
ShutdownServer("C");
}


}

}

}
}
