public class Plan1571771967488 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

}

for (int i = 0; i < 5 ; i++) {
StartServer("B");
StartServer("A");

}



} else {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
}

DecreaseTraffic("A");

for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("C");
DecreaseTraffic("A");


}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



}

}
}
