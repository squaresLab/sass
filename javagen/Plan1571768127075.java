public class Plan1571768127075 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("B");

for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
StartServer("C");

}

} else {
StartServer("B");
}


}

}
}
