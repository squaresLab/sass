public class Plan1571767654360 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
DecreaseDimmer("B");
}

DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
StartServer("B");

}

}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}





}
}
