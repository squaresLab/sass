public class Plan1571771560932 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

}

StartServer("A");

}

} else {
DecreaseTraffic("C");
}

DecreaseTraffic("A");

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


}
}
