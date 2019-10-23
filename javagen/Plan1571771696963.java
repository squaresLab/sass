public class Plan1571771696963 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
if ( StartServer("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
DecreaseDimmer("B");
}

}

StartServer("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
