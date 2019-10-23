public class Plan1571772545675 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("C");
StartServer("B");

DecreaseTraffic("A");

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
StartServer("B");
}

}



}
}
